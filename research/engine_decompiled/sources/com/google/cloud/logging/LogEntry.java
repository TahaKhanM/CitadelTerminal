/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.HttpRequest;
import com.google.cloud.logging.Operation;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.SourceLocation;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.logging.v2.LogEntry;
import com.google.logging.v2.LogEntryOperation;
import com.google.logging.v2.LogEntrySourceLocation;
import com.google.logging.v2.ProjectLogName;
import com.google.protobuf.Timestamp;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LogEntry
implements Serializable {
    private static final long serialVersionUID = -944788159728228219L;
    private static final long NANOS_PER_MILLISECOND = 1000000L;
    private static final long MILLIS_PER_SECOND = 1000L;
    static final Function<com.google.logging.v2.LogEntry, LogEntry> FROM_PB_FUNCTION = new Function<com.google.logging.v2.LogEntry, LogEntry>(){

        @Override
        public LogEntry apply(com.google.logging.v2.LogEntry pb) {
            return LogEntry.fromPb(pb);
        }
    };
    private final String logName;
    private final MonitoredResource resource;
    private final Long timestamp;
    private final Long receiveTimestamp;
    private final Severity severity;
    private final String insertId;
    private final HttpRequest httpRequest;
    private final Map<String, String> labels;
    private final Operation operation;
    private final String trace;
    private final String spanId;
    private final SourceLocation sourceLocation;
    private final Payload<?> payload;

    LogEntry(Builder builder) {
        this.logName = builder.logName;
        this.resource = builder.resource;
        this.timestamp = builder.timestamp;
        this.receiveTimestamp = builder.receiveTimestamp;
        this.severity = builder.severity;
        this.insertId = builder.insertId;
        this.httpRequest = builder.httpRequest;
        this.labels = ImmutableMap.copyOf(builder.labels);
        this.operation = builder.operation;
        this.trace = builder.trace;
        this.spanId = builder.spanId;
        this.sourceLocation = builder.sourceLocation;
        this.payload = builder.payload;
    }

    public String getLogName() {
        return this.logName;
    }

    public MonitoredResource getResource() {
        return this.resource;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public Long getReceiveTimestamp() {
        return this.receiveTimestamp;
    }

    public Severity getSeverity() {
        return this.severity;
    }

    public String getInsertId() {
        return this.insertId;
    }

    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    public Map<String, String> getLabels() {
        return this.labels;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public String getTrace() {
        return this.trace;
    }

    public String getSpanId() {
        return this.spanId;
    }

    public SourceLocation getSourceLocation() {
        return this.sourceLocation;
    }

    public <T extends Payload> T getPayload() {
        return (T)this.payload;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.logName, this.resource, this.timestamp, this.receiveTimestamp, this.severity, this.insertId, this.httpRequest, this.labels, this.operation, this.trace, this.spanId, this.sourceLocation, this.payload});
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogEntry)) {
            return false;
        }
        LogEntry other = (LogEntry)obj;
        return Objects.equals(this.logName, other.logName) && Objects.equals(this.resource, other.resource) && Objects.equals(this.timestamp, other.timestamp) && Objects.equals(this.receiveTimestamp, other.receiveTimestamp) && Objects.equals((Object)this.severity, (Object)other.severity) && Objects.equals(this.insertId, other.insertId) && Objects.equals(this.httpRequest, other.httpRequest) && Objects.equals(this.labels, other.labels) && Objects.equals(this.operation, other.operation) && Objects.equals(this.trace, other.trace) && Objects.equals(this.spanId, other.spanId) && Objects.equals(this.sourceLocation, other.sourceLocation) && Objects.equals(this.payload, other.payload);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logName", this.logName).add("resource", this.resource).add("timestamp", this.timestamp).add("receiveTimestamp", this.receiveTimestamp).add("severity", (Object)this.severity).add("insertId", this.insertId).add("httpRequest", this.httpRequest).add("labels", this.labels).add("operation", this.operation).add("trace", this.trace).add("spanId", this.spanId).add("sourceLocation", this.sourceLocation).add("payload", this.payload).toString();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    private static Timestamp timestampFromMillis(Long millis) {
        Timestamp.Builder tsBuilder = Timestamp.newBuilder();
        tsBuilder.setSeconds(millis / 1000L);
        tsBuilder.setNanos((int)(millis % 1000L * 1000000L));
        return tsBuilder.build();
    }

    private static Long millisFromTimestamp(Timestamp timestamp) {
        return timestamp.getSeconds() * 1000L + (long)timestamp.getNanos() / 1000000L;
    }

    com.google.logging.v2.LogEntry toPb(String projectId) {
        LogEntry.Builder builder = this.payload.toPb();
        builder.putAllLabels(this.labels);
        if (this.logName != null) {
            builder.setLogName(ProjectLogName.of(projectId, this.logName).toString());
        }
        if (this.resource != null) {
            builder.setResource(this.resource.toPb());
        }
        if (this.timestamp != null) {
            builder.setTimestamp(LogEntry.timestampFromMillis(this.timestamp));
        }
        if (this.receiveTimestamp != null) {
            builder.setReceiveTimestamp(LogEntry.timestampFromMillis(this.receiveTimestamp));
        }
        if (this.severity != null) {
            builder.setSeverity(this.severity.toPb());
        }
        if (this.insertId != null) {
            builder.setInsertId(this.insertId);
        }
        if (this.httpRequest != null) {
            builder.setHttpRequest(this.httpRequest.toPb());
        }
        if (this.operation != null) {
            builder.setOperation(this.operation.toPb());
        }
        if (this.trace != null) {
            builder.setTrace(this.trace);
        }
        if (this.spanId != null) {
            builder.setSpanId(this.spanId);
        }
        if (this.sourceLocation != null) {
            builder.setSourceLocation(this.sourceLocation.toPb());
        }
        return builder.build();
    }

    public static Builder newBuilder(Payload<?> payload) {
        return new Builder(payload);
    }

    public static LogEntry of(Payload<?> payload) {
        return LogEntry.newBuilder(payload).build();
    }

    public static LogEntry of(String logName, MonitoredResource resource, Payload<?> payload) {
        return LogEntry.newBuilder(payload).setLogName(logName).setResource(resource).build();
    }

    static LogEntry fromPb(com.google.logging.v2.LogEntry entryPb) {
        Long millis;
        Builder builder = LogEntry.newBuilder(Payload.fromPb(entryPb));
        builder.setLabels(entryPb.getLabelsMap());
        builder.setSeverity(Severity.fromPb(entryPb.getSeverity()));
        if (!entryPb.getLogName().equals("")) {
            builder.setLogName(ProjectLogName.parse(entryPb.getLogName()).getLog());
        }
        if (!entryPb.getResource().equals(com.google.api.MonitoredResource.getDefaultInstance())) {
            builder.setResource(MonitoredResource.fromPb(entryPb.getResource()));
        }
        if (entryPb.hasTimestamp() && (millis = LogEntry.millisFromTimestamp(entryPb.getTimestamp())) != 0L) {
            builder.setTimestamp(millis);
        }
        if (entryPb.hasReceiveTimestamp() && (millis = LogEntry.millisFromTimestamp(entryPb.getReceiveTimestamp())) != 0L) {
            builder.setReceiveTimestamp(millis);
        }
        if (!entryPb.getInsertId().equals("")) {
            builder.setInsertId(entryPb.getInsertId());
        }
        if (!entryPb.getHttpRequest().equals(com.google.logging.type.HttpRequest.getDefaultInstance())) {
            builder.setHttpRequest(HttpRequest.fromPb(entryPb.getHttpRequest()));
        }
        if (!entryPb.getOperation().equals(LogEntryOperation.getDefaultInstance())) {
            builder.setOperation(Operation.fromPb(entryPb.getOperation()));
        }
        if (!entryPb.getTrace().equals("")) {
            builder.setTrace(entryPb.getTrace());
        }
        if (!entryPb.getSpanId().equals("")) {
            builder.setSpanId(entryPb.getSpanId());
        }
        if (!entryPb.getSourceLocation().equals(LogEntrySourceLocation.getDefaultInstance())) {
            builder.setSourceLocation(SourceLocation.fromPb(entryPb.getSourceLocation()));
        }
        return builder.build();
    }

    static Function<LogEntry, com.google.logging.v2.LogEntry> toPbFunction(final String projectId) {
        return new Function<LogEntry, com.google.logging.v2.LogEntry>(){

            @Override
            public com.google.logging.v2.LogEntry apply(LogEntry entry) {
                return entry.toPb(projectId);
            }
        };
    }

    public static class Builder {
        private String logName;
        private MonitoredResource resource;
        private Long timestamp;
        private Long receiveTimestamp;
        private Severity severity = Severity.DEFAULT;
        private String insertId;
        private HttpRequest httpRequest;
        private Map<String, String> labels = new HashMap<String, String>();
        private Operation operation;
        private String trace;
        private String spanId;
        private SourceLocation sourceLocation;
        private Payload<?> payload;

        Builder(Payload<?> payload) {
            this.payload = payload;
        }

        Builder(LogEntry entry) {
            this.logName = entry.logName;
            this.resource = entry.resource;
            this.timestamp = entry.timestamp;
            this.receiveTimestamp = entry.receiveTimestamp;
            this.severity = entry.severity;
            this.insertId = entry.insertId;
            this.httpRequest = entry.httpRequest;
            this.labels = new HashMap<String, String>(entry.labels);
            this.operation = entry.operation;
            this.trace = entry.trace;
            this.spanId = entry.spanId;
            this.sourceLocation = entry.sourceLocation;
            this.payload = entry.payload;
        }

        public Builder setLogName(String logName) {
            this.logName = logName;
            return this;
        }

        public Builder setResource(MonitoredResource resource) {
            this.resource = resource;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setReceiveTimestamp(long receiveTimestamp) {
            this.receiveTimestamp = receiveTimestamp;
            return this;
        }

        public Builder setSeverity(Severity severity) {
            this.severity = severity;
            return this;
        }

        public Builder setInsertId(String insertId) {
            this.insertId = insertId;
            return this;
        }

        public Builder setHttpRequest(HttpRequest httpRequest) {
            this.httpRequest = httpRequest;
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

        public Builder setOperation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public Builder setTrace(String trace2) {
            this.trace = trace2;
            return this;
        }

        public Builder setSpanId(String spanId) {
            this.spanId = spanId;
            return this;
        }

        public Builder setSourceLocation(SourceLocation sourceLocation) {
            this.sourceLocation = sourceLocation;
            return this;
        }

        public Builder setPayload(Payload payload) {
            this.payload = payload;
            return this;
        }

        public LogEntry build() {
            return new LogEntry(this);
        }
    }
}

