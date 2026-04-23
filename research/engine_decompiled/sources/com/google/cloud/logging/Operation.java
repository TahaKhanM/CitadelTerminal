/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogEntryOperation;
import java.io.Serializable;
import java.util.Objects;

public final class Operation
implements Serializable {
    private static final long serialVersionUID = 1586890644894328269L;
    private final String id;
    private final String producer;
    private final boolean first;
    private final boolean last;

    Operation(Builder builder) {
        this.id = Preconditions.checkNotNull(builder.id);
        this.producer = Preconditions.checkNotNull(builder.producer);
        this.first = builder.first;
        this.last = builder.last;
    }

    public String getId() {
        return this.id;
    }

    public String getProducer() {
        return this.producer;
    }

    public boolean first() {
        return this.first;
    }

    public boolean last() {
        return this.last;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Operation)) {
            return false;
        }
        Operation other = (Operation)obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.producer, other.producer) && Objects.equals(this.first, other.first) && Objects.equals(this.last, other.last);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.producer, this.first, this.last);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", this.id).add("producer", this.producer).add("first", this.first).add("last", this.last).toString();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    LogEntryOperation toPb() {
        LogEntryOperation.Builder builder = LogEntryOperation.newBuilder();
        builder.setId(this.id);
        builder.setProducer(this.producer);
        builder.setFirst(this.first);
        builder.setLast(this.last);
        return builder.build();
    }

    public static Builder newBuilder(String id, String producer) {
        return new Builder(id, producer);
    }

    public static Operation of(String id, String producer) {
        return Operation.newBuilder(id, producer).build();
    }

    static Operation fromPb(LogEntryOperation operationPb) {
        return Operation.newBuilder(operationPb.getId(), operationPb.getProducer()).setFirst(operationPb.getFirst()).setLast(operationPb.getLast()).build();
    }

    public static final class Builder {
        private String id;
        private String producer;
        private boolean first;
        private boolean last;

        Builder(String id, String producer) {
            this.id = id;
            this.producer = producer;
        }

        Builder(Operation operation) {
            this.id = operation.id;
            this.producer = operation.producer;
            this.first = operation.first;
            this.last = operation.last;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setProducer(String producer) {
            this.producer = producer;
            return this;
        }

        public Builder setFirst(boolean first) {
            this.first = first;
            return this;
        }

        public Builder setLast(boolean last2) {
            this.last = last2;
            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }
}

